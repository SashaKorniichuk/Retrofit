using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Web.Store.Data.Entities;

namespace Web.Store.Data.Seed
{
    public class UserRoleSeed
    {
        public static async Task SeedRoleData(RoleManager<AppRole> manager)
        {
            if (!manager.Roles.Any())
            {
                var roles = new List<AppRole>
                {
                    new AppRole
                    {
                        Name = "admin",
                    },                 
                    new AppRole
                    {
                        Name = "participant",
                    }
                };
                foreach (var role in roles)
                {
                    await manager.CreateAsync(role);
                }
            }
        }
    }
}
